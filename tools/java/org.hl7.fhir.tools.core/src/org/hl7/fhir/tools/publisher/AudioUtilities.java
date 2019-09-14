package org.hl7.fhir.tools.publisher;

/*-
 * #%L
 * org.hl7.fhir.utilities
 * %%
 * Copyright (C) 2014 - 2019 Health Level 7
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

import org.hl7.fhir.utilities.Utilities;

/**
 * Utilities for working with sound
 * <p>
 * This is separate from {@link Utilities} in order to avoid introducing a mandatory
 * dependency on the javax.sound libraries for anyone using just the structures
 * </p>
 */
public class AudioUtilities {

  // http://stackoverflow.com/questions/3780406/how-to-play-a-sound-alert-in-a-java-application
  public static float SAMPLE_RATE = 8000f;

  public static void tone(int hz, int msecs) {
    tone(hz, msecs, 1.0);
  }

  public static void tone(int hz, int msecs, double vol) {
    try {
      byte[] buf = new byte[1];
      AudioFormat af =
        new AudioFormat(
          SAMPLE_RATE, // sampleRate
          8,           // sampleSizeInBits
          1,           // channels
          true,        // signed
          false);      // bigEndian
      SourceDataLine sdl;
      sdl = AudioSystem.getSourceDataLine(af);
      sdl.open(af);
      sdl.start();
      for (int i = 0; i < msecs * 8; i++) {
        double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
        buf[0] = (byte) (Math.sin(angle) * 127.0 * vol);
        sdl.write(buf, 0, 1);
      }
      sdl.drain();
      sdl.stop();
      sdl.close();
    } catch (Exception e) {
    }
  }


}

#!/bin/bash
for i in `find .  -size +100M`; do rm $i; done

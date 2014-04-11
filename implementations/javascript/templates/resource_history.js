// Copyright (c) 2011-2013, HL7, Inc & Mitre
// All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without modification, 
// are permitted provided that the following conditions are met:
// 
//  * Redistributions of source code must retain the above copyright notice, this 
//    list of conditions and the following disclaimer.
//  * Redistributions in binary form must reproduce the above copyright notice, 
//    this list of conditions and the following disclaimer in the documentation 
//    and/or other materials provided with the distribution.
//  * Neither the name of HL7 nor the names of its contributors may be used to 
//    endorse or promote products derived from this software without specific 
//    prior written permission.
// 
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
// ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
// WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
// IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
// INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
// NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
// PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
// WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
// POSSIBILITY OF SUCH DAMAGE.

var mongoose = require('mongoose');
var _ = require('underscore');
var async = require('async');

var ResourceHistorySchema = new mongoose.Schema({
  resourceType: String,
  vistaId: String,
  history: [{resourceId: mongoose.Schema.Types.ObjectId, createdAt: Date}]
});

ResourceHistorySchema.methods = {
  addVersion: function (resourceId) {
    this.history.push({resourceId: resourceId, createdAt: Date.now()});
  },

  getVersion: function (version, callback) {
    var resourceModel = mongoose.model(this.resourceType);
    resourceModel.findOne(this.getVersionId(version), function(err, instance){
      callback(err, instance);
    });
  },

  getVersionId: function (version) {
    return this.history[version-1].resourceId.toHexString();
  },

  versionCount: function () {
    return this.history.length;
  },

  lastUpdatedAt: function () {
    return _.last(this.history).createdAt;
  },

  latestVersionId: function () {
    return _.last(this.history).resourceId.toHexString();
  },

  findLatest: function(callback) {
    var resourceModel = mongoose.model(this.resourceType);
    resourceModel.findById(this.latestVersionId(), function(err, instance) {
      callback(err, instance);
    });
  }
};

ResourceHistorySchema.statics = {
  findInCacheOrLocal: function (resourceId, resourceType, cb) {
    var self = this;
    async.waterfall([
      function(callback) {
        self.findOne({vistaId: resourceId, "resourceType": resourceType}, function(err, resourceHistory) {
          callback(err, resourceHistory);
        });
      },
      function(resourceHistory, callback) {
        if (resourceHistory) {
          callback(resourceHistory);
        } else {
          self.findById(resourceId, function(err, resourceHistory) {
            callback(resourceHistory);
          });
        }
      }
    ], function(resourceHistory) {
      cb(resourceHistory);
    });
  }
};

mongoose.model('ResourceHistory', ResourceHistorySchema);
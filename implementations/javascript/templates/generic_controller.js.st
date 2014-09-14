// Copyright (c) 2011+, HL7, Inc & The MITRE Corporation
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
var fs = require('fs');
var eco = require('eco');
var async = require('async');
var <ModelName> = mongoose.model('<ModelName>');
var ResourceHistory = mongoose.model('ResourceHistory');
var ResponseFormatHelper = require(__dirname + '/../../lib/response_format_helper');

exports.load = function(req, res, id, vid, next) {
  if (req.resourceHistory) {
    if(vid !== null){
      req.resourceHistory.getVersion(vid, function(err, <LowerCaseModelName>) {
        req.<LowerCaseModelName> = <LowerCaseModelName>;
        next(<LowerCaseModelName>);
      });
    } else {
      req.resourceHistory.findLatest(function(err, <LowerCaseModelName>) {
        req.<LowerCaseModelName> = <LowerCaseModelName>;
        next(<LowerCaseModelName>);
      });
    }
  } else {
    ResourceHistory.findOne(id, function(rhErr, resourceHistory) {
      if (rhErr) {
        next(rhErr);
      }
      if(resourceHistory !== null) {
        req.resourceHistory = resourceHistory;
        req.resourceHistory.findLatest(function(err, <LowerCaseModelName>) {
          req.<LowerCaseModelName> = <LowerCaseModelName>;
          next(<LowerCaseModelName>);
        });
      }
    });
  }
};

exports.show = function(req, res) {
  var <LowerCaseModelName> = req.<LowerCaseModelName>;
  var json = JSON.stringify(<LowerCaseModelName>);
  res.send(json);
};

exports.create = function(req, res) {
  var <LowerCaseModelName> = new <ModelName>(req.body);
  <LowerCaseModelName>.save(function(err, saved<ModelName>) {
    if(err) {
      res.send(500);
    } else {
      var resourceHistory = new ResourceHistory({resourceType: '<ModelName>'});
      resourceHistory.addVersion(saved<ModelName>.id);
      resourceHistory.save(function(rhErr, savedResourceHistory){
        if (rhErr) {
          res.send(500);
        } else {
          res.set('Location', ("http://localhost:3000/<LowerCaseModelName>/@" + resourceHistory.id));
          res.send(201);
        }
      });
    }
  });
};

exports.update = function(req, res) {
  var <LowerCaseModelName> = req.<LowerCaseModelName>;
  <LowerCaseModelName> = _.extend(<LowerCaseModelName>, req.body);
  <LowerCaseModelName>.save(function(err, saved<LowerCaseModelName>) {
    if(err) {
      res.send(500);
    } else {
      var resourceHistory = req.resourceHistory;
      resourceHistory.addVersion(saved<LowerCaseModelName>);
      resourceHistory.save(function(rhErr, savedResourceHistory) {
        if (rhErr) {
          res.send(500);
        } else {
          res.send(200);
        }
      });
    }
  });
};

exports.destroy = function(req, res) {
  var <LowerCaseModelName> = req.<LowerCaseModelName>;
  <LowerCaseModelName>.remove(function (err) {
    if(err) {
      res.send(500);
    } else {
      res.send(204);
    }
  });
};

exports.list = function(req, res) {

  var content = {
    title: "Search results for resource type <ModelName>",
    id: "http://localhost:3000/<LowerCaseModelName>",
    totalResults: 0,
    link: {
      href: "http://localhost:3000/<LowerCaseModelName>",
      rel: "self"
    },
    updated: new Date(Date.now()),
    entry: []
  };

  ResourceHistory.find({resourceType:"<ModelName>"}, function (rhErr, histories) {
    if (rhErr) {
      return next(rhErr);
    }
    var counter = 0;
    async.forEach(histories, function(history, callback) {
      counter++;
      content.totalResults = counter;
      history.findLatest( function(err, <LowerCaseModelName>) {
        var entrywrapper = {
          title: "<ModelName> " + history.vistaId + " Version " + history.versionCount(),
          id: "http://localhost:3000/<LowerCaseModelName>/@" + history.vistaId,
          link: {
            href: "http://localhost:3000/<LowerCaseModelName>/@" + history.vistaId + "/history/@" + history.versionCount(),
            rel: "self"
          },
          updated: history.lastUpdatedAt(),
          published: new Date(Date.now()),
          content: <LowerCaseModelName>
        };
        content.entry.push(entrywrapper);
        callback();
      });
    }, function(err) {
        res.send(JSON.stringify(content));
    });
  });
};
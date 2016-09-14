# GoFHIR Indexes Configuration
#
# GoFHIR runs db.collection.ensureIndex() on startup for each of the indexes enabled in this file.
# We use mongo's default naming convention for our indexes, see:
# https://docs.mongodb.com/manual/reference/method/db.collection.createIndex/#options-for-all-index-types
#
# The indexes in this file are grouped by resource and organized into two categories:
# 1. Required - to perform queries at scale GoFHIR requires these indexes
# 2. Optional - users can add additional indexes here if their specific use case requires them
#
# By default GoFHIR automatically creates compound indexes on all reference fields for each resource. These
# indexes compound the referenceid and type fields. For more information on compound indexes see:
# https://docs.mongodb.com/manual/core/index-compound/
#
# Standard indexes in this file should have the following format:
# \<collection_name>.\<key>_(-)1
# 
# Where the sort order can be specified as 1 (ascending) or -1 (descending).
# 
# Compound indexes in this file should have the following format:
# \<collection_name>.(\<key1>_(-)1, \<key2>_(-)1, ...)

<ResourceIndexInfos: {r |
# -------------------------------------------------------------------------------------------------
# Collection: <r.name>
# -------------------------------------------------------------------------------------------------
# Required Indexes:
<if(r.indexes)><r.indexes: {idx |
<r.name>.(<idx.name>.referenceid_1, <idx.name>.type_1)
}>
<else># No required indexes for this resource

<endif>
# Optional Indexes:
# You can add additional indexes here if needed

}>
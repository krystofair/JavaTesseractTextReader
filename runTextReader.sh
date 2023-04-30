#!/bin/bash

# Examine this script to change the most important parts.

export TESSDATA_PREFIX=./tessdata  # set where to find tessdata

# create dir for ready texts if not exists yet
if [[ ! -d ready ]]; then
    mkdir ready
fi

# prepare jar executable if not exists yet
if [[ ! -e ./text-from-jpg-reader.jar ]]
then
    mv ./target/text-from-jpg-reader-1.0-SNAPSHOT-jar-with-dependencies.jar ./text-from-jpg-reader.jar
fi

# check if prepare jar exec is done correctly
if [[ ! $? -eq 0 ]]
then
    exit -1;
fi

# processed each picture in specified directory
# or skip if txt version for that file exists
for file in $(ls /srv/untrusted/pictures)
do
    if [[ -d ./ready/${file}.txt ]]; then continue; fi
    java -jar ./text-from-jpg-reader.jar $file > ./ready/${file}.txt
done

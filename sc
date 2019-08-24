#!/bin/bash

echo "Usage : $0 <url-starts-after-courses/> <video_name_to_be_saved>"

if [ $1 ]
then
echo "downloading for url : \n\n\n : " $1
echo "file Name: " $2
if [ $2 ]
then
downloadFileName=$2
else
downloadFileName=`echo $2 | cut -d"-" -f2`
fi
else
echo "url not provided  "
exit
fi


mfile='https://s3.ap-south-1.amazonaws.com/videoin.gfg.org/courses/'$1

rm url.txt
echo "downloading for file  + "$2
echo $mfile

curl $mfile -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36' -H 'Referer: https://practice.geeksforgeeks.org/tracks/SPC-Intro/?batchId=154' -H 'Origin: https://practice.geeksforgeeks.org' | grep '.ts' > url.txt 

input="./url.txt"
while IFS= read -r line
do
#echo 'https://s3.ap-south-1.amazonaws.com/videoin.gfg.org/courses/'"$line"
curl 'https://s3.ap-south-1.amazonaws.com/videoin.gfg.org/courses/'$line -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36' -H 'Referer: https://practice.geeksforgeeks.org/tracks/SPC-Intro/?batchId=154' -H 'Origin: https://practice.geeksforgeeks.org' >> $downloadFileName

#Introduced sleep to make it more real
sleep 1s
done < "$input"



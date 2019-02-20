read -p "Remove all unnecessary files in $(pwd)? " -n 1 -r
echo
if [[ $REPLY =~ ^[Yy]$ ]]
then
   find -name *\.bak -delete
   find -name *\.class -delete
   find temp/.  -type f -delete
   find publish/. -type f  -delete
fi

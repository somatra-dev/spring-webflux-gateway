
echo "Type your commit message below: ";
read message;

git add .
git commit -m "${message}"
git push -u origin main

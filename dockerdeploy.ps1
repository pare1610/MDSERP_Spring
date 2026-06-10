# To run in PowerShell, save this script as dockerdeploy.ps1 and run it in the terminal with the command ./dockerdeploy.ps1
# Stop and remove the existing container if it exists.
docker rm -f mdsback-container 2>$null | Out-Null

# Remove the previous image if it exists.
docker rmi  mdserp:1.0.0 2>$null | Out-Null

# Build the new image from the current Dockerfile.
docker build -t  mdserp:1.0.0 .

# Run the container in detached mode and restart it automatically unless stopped manually.
docker run -d --name mdsback-container --restart unless-stopped -p 8181:8181  mdserp:1.0.0
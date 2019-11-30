mvn clean package
docker image rm vmarci94/me-fdsz-be
docker build -f Dockerfile.dev -t vmarci94/me-fdsz-be .


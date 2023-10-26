#!/bin/zsh
set -e

# build and deploy
./mvnw clean install

gcloud app deploy --quiet

PROD_URL=https://backend.vivekraman.dev

# fetch openAPI 3.0 spec
FILE_PATH_3_0=./target/api-spec-3-0.json
curl $PROD_URL/v3/api-docs -o $FILE_PATH_3_0

# convert to openAPI 2.0
FILE_PATH_2_0=./target/api-spec-2-0.yaml
api-spec-converter --from=openapi_3 --to=swagger_2 --syntax=yaml $FILE_PATH_3_0 > $FILE_PATH_2_0

# update API config

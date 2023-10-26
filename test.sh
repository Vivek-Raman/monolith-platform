#!/bin/zsh

MY_PORT=9000
FILE_PATH_3_0=./target/api-spec-3-0.json
curl http://localhost:$MY_PORT/v3/api-docs -o $FILE_PATH_3_0

# convert to openAPI 2.0
FILE_PATH_2_0=./target/api-spec-2-0.yaml
api-spec-converter --from=openapi_3 --to=swagger_2 --syntax=yaml $FILE_PATH_3_0 > $FILE_PATH_2_0

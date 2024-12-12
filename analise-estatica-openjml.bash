#!/bin/bash

start_time=$(date +%s)

SOURCE_DIR="./src/main/java/br/ufrn/bancojml"

SOURCE_FILES=(
    $SOURCE_DIR/Main.java
    $SOURCE_DIR/dominio/Titular.java
)

echo exec: openjml --esc

for FILE_PATH in "${SOURCE_FILES[@]}"; do
    RELATIVE_PATH=$(realpath --relative-to="$SOURCE_DIR" "$FILE_PATH")
    echo "  $RELATIVE_PATH"
done

openjml --esc "${SOURCE_FILES[@]}"

end_time=$(date +%s)
execution_time=$((end_time - start_time))

echo -e "\nTempo de execução: $execution_time segundos"

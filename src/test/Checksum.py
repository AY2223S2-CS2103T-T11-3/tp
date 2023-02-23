import zlib
import sys

def calculate_crc32_checksum(fileName):
    with open(fileName, "rb") as f:
        bytes = f.read()
    checksum = zlib.crc32(bytes)
    print(checksum)
    

if __name__ == "__main__" :
    calculate_crc32_checksum(sys.argv[1])

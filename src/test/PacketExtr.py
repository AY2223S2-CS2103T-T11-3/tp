import sys

while True:
    #returns immediately upon new data stream in
    #and may return fewer bytes
    head = sys.stdin.buffer.read1(6)

    if len(head) == 0 :
      return -1;

    byte_array = bytearray()
    digit = sys.stdin.buffer.read1(1)

    while True:
        if data == b'B':
            break
        else: 
        byte_array += bytearray(data).decode()

#    num_bytes_to_read = int(num_bytes)

    num_bytes_to_read = int(num_bytes.split()[1])

    while num_bytes_to_read > 0:
        playload = sys.stdin.buffer.read1(num_bytes_to_read)
        num_bytes_to_read -= len(playload)
        
        sys.stdout.buffer.write(playload)
        sys.stdout.buffer.flush()
        

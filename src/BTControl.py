#!/usr/bin/python
import sys
from bluetooth import *

class BTControl:
	_sock = None
	def __init__(self,address = None):
		# I really should do some validation around here
	
		if address == None:
			address = '00:16:53:04:00:1C'
		self._sock = BluetoothSocket(RFCOMM)
		self._sock.connect((address,1))
	
	def transmit(self,char):
		if len(char) == 1:
			self._sock.send(char)

	def __del__(self):
		self._sock.close()
	
if __name__ == "__main__":
	helptext = """
To run this as a program,
use either the adress of the NXT brick you are trying to reach or nothing at all.
The adress should be formatted as "XX:XX:XX:XX:XX:XX"
"""
	if len(sys.argv) > 2:
		print(helptext)
		sys.exit()
	#start up a default socket and send a, b, c, q
	if len(sys.argv) == 1:
		print("Connecting using default port")
		btc = BTControl()
	if len(sys.argv) == 2:
		print("Connecting using specified port",sys.argv[1])
		btc = BTControl(sys.argv[1])
	for c in ('a','b','c','q'):
		btc.transmit(c)


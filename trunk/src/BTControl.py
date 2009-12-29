#!/usr/bin/python
import sys
from bluetooth import *

class BTSearch:
	def __init__(self):
		pass

	def search(self,name=None):
		addresses = discover_devices()
		#if len(addresses) == 0:
		#	return None
		names = []
		for adr in addresses:
			names.append(lookup_name(adr))
			if name != None and name == names[-1]:
				return adr

		return zip(addresses,names)

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
use either the adress of the NXT brick you are trying to reach,
the name of the brickor nothing at all.
The adress should be formatted as "XX:XX:XX:XX:XX:XX"
The name should not have any ":" in it
"""
	if len(sys.argv) > 2:
		print(helptext)
		sys.exit()
	#start up a default socket and send a, b, c, q
	if len(sys.argv) == 1:
		print("Connecting using default port")
		btc = BTControl()
	if len(sys.argv) == 2:
		if ':' in sys.argv[1]:
			print("Connecting using specified port",sys.argv[1])
			btc = BTControl(sys.argv[1])
		else:
			print("Connecting to brick with name ",sys.argv[1])
			bts = BTSearch()
			address = bts.search(sys.argv[1])
			if type(address) == type('00:00'):
				btc = BTControl(address)
			else:
				print "No bluetooth device found with name ", sys.argv[1]
				sys.exit()

	for c in ('a','b','c','q'):
		btc.transmit(c)


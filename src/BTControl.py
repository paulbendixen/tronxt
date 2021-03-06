#!/usr/bin/python
import sys
from bluetooth import *

class BTSearch:
	"""Search module for bluetooth connections"""
	def __init__(self):
		pass

	def search(self,name=None):
		"""When called with no name parameter will return a list of tuples with
		adresses in the first entry and names in the second.
		When called with the name parameter as a string will either return the address of 
		the named device (as a string) or if it was not found, the same as no name """
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
	"""Class for connecting to a Bluetooth device"""
	_connected = False
	_sock = None
	def __init__(self,address = None):
		"""If called with an adress will attempt to connect to the given address
		otherwise will just create the object"""
		# I really should do some validation around here
	
		if address != None:
			self.connect(address)

	def connect(self,address):
		"""Will try to connect to the given address, using an RFCOMM socket"""
		self._sock = BluetoothSocket(RFCOMM)
		self._sock.connect((address,1))
		#If the socket connect fails, it will raise an error and terminate
		self._connected = True
		
	
	def transmit(self,char):
		"""If a successfull connect has been executed will send one char over the RFCOMM socket"""
		if len(char) == 1 and self._connected == True:
			self._sock.send(char)

	def recieve(self):
		"""If a successfull connect has been executed will recieve one char over the RFCOMM socket"""
		if self._connected == True:
			return self._sock.recv(1)

	def __del__(self):
		if self._connected:
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
	btc = BTControl()
	if len(sys.argv) == 1:
		print("Connecting using default port")
		btc.connect('00:16:53:03:5D:96')
	if len(sys.argv) == 2:
		if ':' in sys.argv[1]:
			print("Connecting using specified port",sys.argv[1])
			btc.connect(sys.argv[1])
		else:
			print("Connecting to brick with name ",sys.argv[1])
			bts = BTSearch()
			address = bts.search(sys.argv[1])
			if type(address) == type('00:00'):
				btc.connect(address)
			else:
				print "No bluetooth device found with name ", sys.argv[1]
				sys.exit()

	for c in ('s','l','r','q'):
		btc.transmit(c)


#!/usr/bin/python

#Thread functions for connecting to a TroNXT.
from threading import *
from BTControl import *

_usedTronThreadsSemaphore = 0
semLock = Lock()

class tronThread(Thread):
	__init__(self,address):
		# does this need to be stored in a Thread.Local variable?
		self.address = address
		Thread.__init__(self)

	def run(self):
		self.btc = BTControl(self.address)
		#begin loop here
		self.btc.send('s')
		self.got = self.btc.recieve(1)
		if self.got == 'o':
			semLock.aquire()
			_usedTronThreadsSemaphore += 1
			semLock.release()
			self.got = self.btc.recieve(1)
			if self.got = 'd':
				semLock.aquire()
				_usedTronThreadsSemaphore -= 1
				self.got = _usedTronThreadsSemaphore
				semLock.release()
				if self.got == 0:
					self.btc.send('w')
				else:
					self.btc.send('d')
		#end loop here
		self.btc.send('q')
		self.btc.close()



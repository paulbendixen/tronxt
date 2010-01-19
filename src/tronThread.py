#!/usr/bin/python

#Thread functions for connecting to a TroNXT.
from threading import *
from BTControl import *


class TronThread(Thread):
	"""Threading class used to serve several TroNXT bikes"""
	trons = 0
	tronsLock = Lock()

	def __init__(self,address):
		"""Initialization of the thread, includes setting up connection with the nxt"""
		if isinstance(address,BTControl):
			self.btc = address
		else:
			self.btc = BTControl(address)
		Thread.__init__(self)

	def run(self):
		"""The runnable part of the thread
		listens for die messages on its own client and finds out if the client was 
		'Last man standing', then replies in the correct manner"""
		self.btc.transmit('s') #begin loop here
		self.got = self.btc.recieve()
		while 1: # self.got != 0:
			print self.getName() + ": self.got = " + self.got
			if self.got == 'o':
				print self.getName() + " added"
				TronThread.tronsLock.acquire()
				TronThread.trons += 1
				TronThread.tronsLock.release()

			if self.got == 'd':
				print self.getName() + " bites the dust"
				TronThread.tronsLock.acquire()
				TronThread.trons -= 1
				self.got = TronThread.trons
				TronThread.tronsLock.release()
				print self.getName() + str(self.got) +" trons left on the wall"
				if self.got == 0:
					self.btc.transmit('w')
				else:
					self.btc.transmit('d')
				break
			self.got = self.btc.recieve()
		#end loop here
		#self.btc.transmit('q')



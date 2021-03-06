#!/usr/bin/python

# tronControl.py

# This is the main program for controlling the Human controlled NTX bike

import sys
from PyQt4 import QtGui
from PyQt4 import QtCore
from BTControl import *
from BTSelect import *

class ControlWindow(QtGui.QWidget):
	_btc = None
	def __init__(self,address = '', parent = None):
		QtGui.QWidget.__init__(self,parent)

		if isinstance(address,BTControl):
			self._btc = address
		else:
			self._btc = BTControl(address)

		self.setWindowTitle('TroNXT')

		left = QtGui.QPushButton('Left')
		left.setFocusPolicy(QtCore.Qt.NoFocus)
		right = QtGui.QPushButton('Right')
		right.setFocusPolicy(QtCore.Qt.NoFocus)
		label = QtGui.QLabel("Use arrow keys to control the bike")

		self.connect(self,QtCore.SIGNAL('leftPress()'),self.transmitL)
		self.connect(self,QtCore.SIGNAL('rightPress()'),self.transmitR)
		self.connect(left,QtCore.SIGNAL('clicked()'),self.transmitL)
		self.connect(right,QtCore.SIGNAL('clicked()'),self.transmitR)

		vbox = QtGui.QVBoxLayout()
		hbox = QtGui.QHBoxLayout()

		hbox.addWidget(left)
		hbox.addWidget(right)
		vbox.addLayout(hbox)
		vbox.addWidget(label)

		self.setLayout(vbox)
		self.resize(250,75)

		#self._btc.transmit('s')
		#if self._btc.recieve() == 'o':
		#	print "Startup successfull"

	def __del__(self):
		pass
		#self._btc.transmit('q')
		#print "Sending end command"

	def transmitL(self):
		self._btc.transmit('l')
		#print 'l'

	def transmitR(self):
		self._btc.transmit('r')
		#print 'r'
	
	def keyPressEvent(self,event):
		#print 'Got Key?'
		if event.key() == QtCore.Qt.Key_Left:
			#Call the left button function
			self.emit(QtCore.SIGNAL('leftPress()'))
		elif event.key() == QtCore.Qt.Key_Right:
			#Call the rgiht button function
			self.emit(QtCore.SIGNAL('rightPress()'))
		elif event.key() == QtCore.Qt.Key_Escape:
			self.close()


if __name__ == "__main__":
	app = QtGui.QApplication(sys.argv)
	if len(sys.argv) < 2:
		address = None
	else:
		address = sys.argv[1]

	if address == None:
		bts = BTSearch()
		nxts = bts.search()
		btsel = BTSelect(nxts)
		if btsel.exec_():
			address = str(btsel.address())
		else:
			QtGui.QMessageBox.warning(self,"No Selected NXTs","No address selected, program will be shut down")
			sys.exit(-1)

	qb = ControlWindow(address)
	qb.show()
	sys.exit(app.exec_())

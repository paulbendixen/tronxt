#!/usr/bin/python

# tronControl.py

# This is the main program for controlling the Human controlled NTX bike

import sys
from PyQt4 import QtGui
from PyQt4 import QtCore
from BTControl import *

class DeadButton(QtGui.QPushButton):
	def __init__(self,text,parent=None):
		QtGui.QPushButton.__init__(self,text,parent)
	
	def keyPressEvent(self,event):
		pass

class MainWindow(QtGui.QWidget):
	_btc = None
	def __init__(self,address = None, parent = None):
		QtGui.QWidget.__init__(self,parent)

		self._btc = BTControl(address)

		self.setWindowTitle('TroNXT')

		left = QtGui.QPushButton('Left')
		left.setFocusPolicy(QtCore.Qt.ClickFocus)
		right = QtGui.QPushButton('Right')
		right.setFocusPolicy(QtCore.Qt.ClickFocus)
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

	def __del__(self):
		self._btc.transmit('q')

	def transmitL(self):
		self._btc.transmit('l')
		print 'l',

	def transmitR(self):
		self._btc.transmit('r')
		print 'r',
	
	def keyPressEvent(self,event):
		print 'Got Key?'
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
	qb = MainWindow(address)
	qb.show()
	sys.exit(app.exec_())

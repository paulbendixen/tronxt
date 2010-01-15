#!/usr/bin/python

import sys
from PyQt4 import QtCore
from PyQt4 import QtGui

# This is a dialog to open when looking for an NXT to connect to

class BTSelect(QtGui.QDialog):
	"""GUI for selecting NXTs, the items parameter should be a list of touples,
	containing addresses in the first entry and names in the second
	If accepted the address of the selected NXT can be found using the self.address() function"""
	def __init__(self,items = [()],parent = None):
		QtGui.QDialog.__init__(self,parent)

		self.setWindowTitle('Select NXT')

		self.retval = ""
		self._items = items
		self.table=QtGui.QTableWidget()
		self.table.setEditTriggers(QtGui.QAbstractItemView.NoEditTriggers)
		self.table.clear()

		ok = QtGui.QPushButton('&Ok')
		cancel = QtGui.QPushButton('&Cancel')

		self.vbox = QtGui.QVBoxLayout()
		self.hbox = QtGui.QHBoxLayout()
		self.hbox.addStretch(1)
		self.hbox.addWidget(ok)
		self.hbox.addWidget(cancel)
		self.vbox.addWidget(self.table)
		self.vbox.addLayout(self.hbox)
		self.setLayout(self.vbox)
		self.connect(ok,QtCore.SIGNAL('clicked()'),self.accept)
		self.connect(cancel,QtCore.SIGNAL('clicked()'),self.reject)
		#move populate out of the init loop so that a repeat search can be made later on
		self.populate()


	def populate(self):
		self.table.setSortingEnabled(False)
		self.table.setRowCount(len(self._items))
		self.table.setColumnCount(2)
		#self.table.setHorzontalHeaderLabels(['Address','Name'])
		for row,item in enumerate(self._items):
			for col,val in enumerate(item):
				try:
					self.table.setItem(row,col,QtGui.QTableWidgetItem(val))
				except:
					print val
	def address(self):
		return self.retval

	def accept(self):
		selected = self.table.selectedItems()
		if len(selected) > 0:
			row = selected[0].row()
			self.retval = self.table.item(row,0).text()
			QtGui.QDialog.accept(self)
		else:
			QtGui.QMessageBox.warning(self,'This is a SELECT box',"You must select an address or a name before clicking ok")
			return 0


if __name__ == "__main__":
	numbers = ['00:00:00:00:00:00', '00:16:53:04:00:1C', '00:16:53:03:5D:96']
	names = ['noone', 'Odin', 'NXT']
	app = QtGui.QApplication(sys.argv)
	selector = BTSelect(zip(numbers,names))

	if selector.exec_():
		print "Returned ", selector.address()

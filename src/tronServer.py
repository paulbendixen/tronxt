#!/usr/bin/python

# A python server for the TroNXT game
# The program starts several connections for bluetooth TroNXTs, but only one human controlled.

import BTControl
import BTSelect
import tronControl
import tronThread
import sys
from PyQt4 import QtGui

cpus = []
app = QtGui.QApplication(sys.argv)

# Get number of players as 1st argument
noPlayers = 2
if len(sys.argv) > 1:
	if isinstance(sys.argv[1], (int,long)):
		noPlayers = sys.argv[1]

# See if the number of adresses match in order to cut away the wait for a scan
# LATER if len(sys.argv) >= (noPlayers+2):

# Create a scan list to select players from
btUnits = BTControl.BTSearch()
nxts = btUnits.search()

for cpu in xrange(noPlayers-1):
	#create a cpucontrolled player thread
	btsel = BTSelect.BTSelect(nxts,"Select opponent no. " + str(cpu))
	if btsel.exec_():
		cpuaddress = str(btsel.address())
		cpuThread = tronThread.TronThread(cpuaddress)
		cpus.append(cpuThread)
		# Remove entry from list
		nxts.pop(zip(*nxts)[0].index(cpuaddress))
		# pops the index selected by, after unzipping, looking for the address
	else:
		QtGui.QMessageBox.warning(None,"No Selected NXTs","No Opponent selected. There will be one less opponent than wanted")

playerSel = BTSelect.BTSelect(nxts,"Select your TroNXT")
if playerSel.exec_():
	address = str(playerSel.address())
else:
	QtGui.QMessageBox.warning(None,"No Selected NXTs","No address selected, program will be shut down")
	sys.exit(-1)


btc = BTControl.BTControl(address)
playerThread = tronThread.TronThread(btc)
control = tronControl.ControlWindow(btc)
control.show()
for cpu in cpus:
	cpu.start()
playerThread.start()
sys.exit(app.exec_())

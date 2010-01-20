#!/usr/bin/python

# A python server for the TroNXT game
# The program starts several connections for bluetooth TroNXTs, but only one human controlled.

import BTControl
import BTSelect
import tronControl
import tronThread
import sys
from PyQt4 import QtGui
import os
import ConfigParser

usage= """Use the program in the following way:
""" + sys.argv[0] + """ [c] [p Players]
Where c assigns a .tronxt setting file in your homedirectory for storing information,
if the file is available, the data present will be used, otherwise search results will be stored.
p assigns how many players (including the human player) should join - default is 2.
"""

cpus = []
nxts=[]
cp = None
app = QtGui.QApplication(sys.argv)

# Get number of players as 1st argument
noPlayers = 2
if len(sys.argv) > 1:
	if 'p' in sys.argv:
		try:
			noPlayers = int(sys.argv[sys.argv.index('p')+1])
			print "Number of players = " + str(noPlayers)
		except IndexError:
			print usage
			exit(-1)
		except TypeError:
			print usage
			exit(-1)
		except ValueError:
			print usage
			print "Invalid number used for p parameter, will continue with 2."
			noPlayers = 2

	if 'c' in sys.argv:
		cp = ConfigParser.RawConfigParser()
		if cp.read(os.path.expanduser('~/.tronxt')) == []:
			print "No configuration file found, will store scan in " + os.path.expanduser('~/.tronxt')
		

if cp == None:
	# Create a scan list to select players from
	btUnits = BTControl.BTSearch()
	nxts = btUnits.search()
else:
	if cp.sections() == []:
		btUnits = BTControl.BTSearch()
		nxts = btUnits.search()
		cp.add_section('Bluetooth')
		for address,name in nxts:
			cp.set('Bluetooth',name,address)
		with open(os.path.expanduser('~/.tronxt'),'wb')as fil:
			cp.write(fil)
	else:
		data = cp.options('Bluetooth')
		for entry in data:
			nxts.append((cp.get('Bluetooth',entry),entry))

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

###################################
# PLEASE DO NOT DELETE THIS FILE! #
#       (c) S!r.ReaDy 2019        #
###################################


import os
import platform
import time
import sys

time.sleep(3)

if platform.system() == "Linux":
    os.system("sudo screen -L -S DiscordBoT sudo java -jar DiscordBot.jar -restart")
else:
    os.system("java -jar DiscordBot.jar")

sys.exit(0)
#!/bin/bash
source /home/ten/.bashrc
systemctl start postgresql-14
su -c "source /home/ten/.bashrc ; genesisInstall" - "ten"
echo "genesisInstall done"
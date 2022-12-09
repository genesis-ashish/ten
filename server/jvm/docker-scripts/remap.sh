#!/bin/bash
source /home/ten/.bashrc
systemctl start postgresql-14
su -c "source /home/ten/.bashrc ; yes | remap --commit" - "ten"
su -c "JvmRun global.genesis.environment.scripts.SendTable -t USER -f /home/ten/run/site-specific/data/user.csv" - "ten"
echo "remap done"

#!/bin/bash
systemctl start postgresql-14
systemctl enable sshd.service
systemctl start sshd.service
su -c "startServer" - "ten"
echo "Logged as ten, starting server"
tail -f /dev/null
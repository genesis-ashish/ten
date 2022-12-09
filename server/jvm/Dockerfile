FROM centos:7.9.2009 as base

SHELL ["/bin/bash", "-c"]

RUN rpm --import /etc/pki/rpm-gpg/RPM-GPG-KEY* &&\
    yum -y install unzip \
    java-11-openjdk-devel \
    openssh-server
RUN if [ $(uname -m) = "x86_64" ] ; then (echo 'y' | yum install https://download.postgresql.org/pub/repos/yum/reporpms/EL-7-x86_64/pgdg-redhat-repo-latest.noarch.rpm) \
    ; else echo 'y' | yum install https://download.postgresql.org/pub/repos/yum/reporpms/EL-7-aarch64/pgdg-redhat-repo-latest.noarch.rpm ;  \
    yum -y install epel-release ;  \
    yum -y install lmdb ;  \
    ln -s /usr/lib64/liblmdb.so.0.0.0 /usr/lib64/liblmdb.so ; fi
RUN yum -y install --nogpgcheck postgresql14-server
RUN useradd ten &&\
    echo ten:ten | chpasswd &&\
    usermod -aG wheel ten &&\
    mkdir /home/ten/run &&\
    mv /usr/bin/systemctl /usr/bin/systemctl.old &&\
    curl https://raw.githubusercontent.com/gdraheim/docker-systemctl-replacement/master/files/docker/systemctl.py > /usr/bin/systemctl &&\
    chmod +x /usr/bin/systemctl
RUN /usr/bin/ssh-keygen -A

FROM base as copy-distributions

WORKDIR /home/ten/run/
COPY ./build/dependencies/genesisproduct*.zip ./build/dependencies/genesis-distribution*.zip ./build/dependencies/ten-site-specific*.zip ./build/dependencies/auth-distribution-*.zip ./
RUN unzip \*.zip &&\
    rm *.zip &&\
    chown -hR ten:ten ../run/

FROM base as unzipped-distributions
COPY --chown=ten:ten --from=copy-distributions /home/ten/run /home/ten/run
RUN chown ten:ten /home/ten/run

FROM unzipped-distributions as setup-genesis

RUN echo "GENESIS_HOME=/home/ten/run/" >> /home/ten/.bashrc &&\
    echo "export GENESIS_HOME" >> /home/ten/.bashrc &&\
    echo "export TERM='xterm'" >> /home/ten/.bashrc &&\
    echo "source \$GENESIS_HOME/genesis/util/setup.sh" >> /home/ten/.bashrc

FROM setup-genesis as configure-db
COPY docker-scripts/configureDB.sh /scripts/configureDB.sh
RUN chmod +x /scripts/configureDB.sh &&\
    scripts/configureDB.sh

FROM configure-db as genesis-install
COPY docker-scripts/genesisInstall.sh /scripts/genesisInstall.sh
RUN chmod +x /scripts/genesisInstall.sh &&\
    /scripts/genesisInstall.sh

FROM genesis-install as remap
COPY docker-scripts/remap.sh /scripts/remap.sh
RUN chmod +x /scripts/remap.sh &&\
   /scripts/remap.sh

FROM remap as entryPoint
COPY docker-scripts/docker-entrypoint.sh /scripts/docker-entrypoint.sh
RUN chmod +x /scripts/docker-entrypoint.sh
ENTRYPOINT ["/scripts/docker-entrypoint.sh"]
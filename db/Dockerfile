FROM mysql:5.7

COPY conf.d/charset.cnf /etc/mysql/conf.d/

COPY lportal.sql /

ENV MYSQL_DATABASE=lportal \
    MYSQL_ROOT_PASSWORD=password

# The container will run all .sql files which are in /docker-entrypoint-initdb.d
ADD lportal.sql /docker-entrypoint-initdb.d
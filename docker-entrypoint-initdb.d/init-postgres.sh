#!/bin/bash
set -e
psql -U postgres -c "CREATE DATABASE phat;"
echo "Init database success"
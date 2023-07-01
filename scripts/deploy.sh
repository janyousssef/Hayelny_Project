#!/bin/bash
sudo rm -f ~/images/*  && git pull && ./scripts/kill.sh && ./scripts/buildrun.sh

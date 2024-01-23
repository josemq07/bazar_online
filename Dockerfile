FROM ubuntu:latest
LABEL authors="keystroke"

ENTRYPOINT ["top", "-b"]
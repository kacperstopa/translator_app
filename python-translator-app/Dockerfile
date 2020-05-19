FROM python:3.8-slim
COPY . /app
WORKDIR /app
RUN python -m pip install --upgrade pip
RUN python -m pip install grpcio
RUN pip install googletrans
RUN pip install --upgrade google-api-python-client
CMD python translator_server.py

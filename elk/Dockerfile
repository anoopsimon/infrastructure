FROM python:3.10

WORKDIR /app

COPY requirements.txt .
# RUN pip install -r requirements.txt

COPY enroll_kibana.py .

ENTRYPOINT ["python", "enroll_kibana.py"]

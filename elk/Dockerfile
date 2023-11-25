# Use the official Kibana image as the base
FROM kibana:7.10.0

# Install Python and pip
USER root
RUN apt-get update && \
    apt-get install -y python3 python3-pip && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Switch back to kibana user
USER kibana

# Install Elasticsearch Python client
RUN python3 -m pip install elasticsearch

# Copy your Python script into the container (assuming it's named setup_script.py)
COPY setup.py /usr/share/kibana/setup.py

# Expose the default Kibana port
EXPOSE 5601

# Run your script (optional, can also be run manually inside the container)
CMD ["python3", "/usr/share/kibana/setup.py"]

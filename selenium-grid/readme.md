# Selenium Grid Docker Compose Setup

This README provides instructions for setting up a Selenium Grid using Docker Compose, which includes nodes for Chrome, Edge, and Firefox. This setup is ideal for running Selenium tests in a containerized environment, ensuring compatibility and ease of deployment across different systems.

## Prerequisites

Before you begin, ensure you have the following installed:
- **Docker**: [Get Docker](https://docs.docker.com/get-docker/)
- **Docker Compose**: [Install Docker Compose](https://docs.docker.com/compose/install/)

## Setup Instructions

1. **Clone Repository:**
   Clone this repository to your local machine or download the `docker-compose.yml` file.

2. **Navigate to the Project Directory:**
   Open a terminal and navigate to the directory containing the `docker-compose.yml` file.

3. **Start Selenium Grid:**
   Run the following command to start the Selenium Grid with Chrome, Edge, and Firefox nodes:
   ```bash
   docker-compose up -d

This command will download the necessary Docker images and start the containers.

## Access the Selenium Grid Console:

Open a web browser and go to [http://localhost:4444](http://localhost:4444). This is the Selenium Grid console where you can view the status of the hub and nodes.

## Running Tests

To run your Selenium tests, configure your test scripts to connect to the Selenium Grid using the hub URL [http://localhost:4444/wd/hub](http://localhost:4444/wd/hub).

## Maintenance Instructions

### Updating Browser Versions:

To update the browser versions, modify the image tags in the `docker-compose.yml` file. For example, change `selenium/node-chrome:4.0.0` to a newer version like `selenium/node-chrome:4.x.x`. Then, run `docker-compose up -d` to pull the new images and restart the containers.

### Monitoring and Logs:

Keep an eye on the logs of the Docker containers to troubleshoot any issues. Use the command `docker logs [container_name]` for this purpose.

### Regularly Check for Updates:

Regularly check for updates to Selenium and browser images. Keeping the system up to date ensures security and access to the latest features.

### Cleaning Up:

To stop and remove the containers, run `docker-compose down`. This will stop the Selenium Grid and remove all associated containers.


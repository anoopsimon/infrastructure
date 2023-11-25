import time
import requests
from elasticsearch import Elasticsearch

# Elasticsearch settings
es_host = 'localhost'
es_port = 9200
es_user = 'elastic'  # Default superuser
es_pass = 'password' # Replace with your password
kibana_port = 5601

# Function to wait for Elasticsearch to start
def wait_for_es():
    print("Waiting for Elasticsearch to start...")
    while True:
        try:
            res = requests.get(f'http://{es_host}:{es_port}')
            if res.status_code == 200:
                print("Elasticsearch is up!")
                break
        except requests.exceptions.RequestException:
            pass
        time.sleep(5)

# Function to create an admin user (assuming X-Pack security)
def create_admin_user():
    print("Creating admin user...")
    es = Elasticsearch([{'host': es_host, 'port': es_port}], http_auth=(es_user, es_pass))
    # Replace with desired username and password for the admin user
    admin_user = "admin"
    admin_pass = "admin_pass"
    # Elasticsearch user creation API call
    es.security.put_user(username=admin_user, body={
        "password" : admin_pass,
        "roles" : ["superuser"],
        "full_name" : "Admin User",
        "email" : "admin@example.com"
    })
    print(f"Admin user '{admin_user}' created.")

# Function to create a sample index
def create_sample_index():
    print("Creating a sample index...")
    es = Elasticsearch([{'host': es_host, 'port': es_port}], http_auth=(es_user, es_pass))
    es.indices.create(index='sample_index', ignore=400)
    print("Sample index created.")

# Main execution
if __name__ == "__main__":
    wait_for_es()
    create_admin_user()
    create_sample_index()
    print("Setup completed.")

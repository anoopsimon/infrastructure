import subprocess

def generate_enrollment_token():
    command = ["bin/elasticsearch-create-enrollment-token", "-s", "kibana"]
    output = subprocess.check_output(command).decode("utf-8")
    return output.strip()

def enroll_kibana(token):
    command = ["bin/kibana-setup", "--enrollment-token", token]
    subprocess.check_call(command)

if __name__ == "__main__":
    token = generate_enrollment_token()
    enroll_kibana(token)

import subprocess
from typing import Tuple


def run_cmd_without_output(cmd: str) -> None:
    process = subprocess.Popen(
        cmd,
        shell=True, stdout=subprocess.PIPE
    )
    process.wait()


def run_cmd_with_output(cmd: str) -> Tuple[str, str]:
    process = subprocess.run(
        cmd.split(' '),
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE
    )
    return process.stdout.decode(), process.stderr.decode()


def run_cmd_directly(cmd: str) -> None:
    subprocess.run(
        cmd.split(' '),
        shell=False
    )

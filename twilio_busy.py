import twilio
from twilio.rest import Client
import time

account_sid = "?"
auth_token = "?"
client = Client(account_sid, auth_token)


def terminate_call(sid):
    call = client.calls(sid).update(status='completed')
    client.calls(sid).delete()


def check_number_busy(phone_number):
    call = client.calls.create(
        method='GET',
        url='http://demo.twilio.com/docs/voice.xml',
        to=phone_number,
        from_='+16508806260',
        # timeout=3,
    )
    time.sleep(3)
    call_sid = call.sid
    # for call in client.calls.list():
    # print('a')
    # client.calls(call.sid).delete()
    # print("status:", call.status, "id:", call.sid)

    # for busy_call in calls:
    print(client.calls.list()[0].status)
    if client.calls.list()[0].status == "busy" or client.calls.list()[0].status == "in-progress":
        assert(client.calls.list()[0].sid == call_sid)
        terminate_call(call_sid)
        return "busy"
    terminate_call(call_sid)
    return "not busy"
    # for call in client.calls.list():
    #     print("call", call.sid, call.status)
    #     if call.sid == call_sid:
    #         terminate_call(call_sid)
    #         return "busy"
    # terminate_call(call_sid)
    # # client.calls.
    # return "not busy"


if __name__ == "__main__":
    print(check_number_busy("+16505461126"))

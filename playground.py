import twilio
from twilio.rest import Client
# # Your Account Sid and Auth Token from twilio.com/console
# account_sid = 'AC6f773e33d173baceaddd67ddc7f3d124'
# auth_token = '67d7171a58c916ab3531e8a572aa24cc'
# client = Client(account_sid, auth_token)

# call = client.calls.create(
# 	method='GET',
# 	status_callback='https://www.myapp.com/events',
# 	status_callback_event=['initiated', 'answered'],
# 	status_callback_method='POST',
# 	url='http://demo.twilio.com/docs/voice.xml',
# 	to='+16505461126',
# 	from_='+16508806260'
# )



# print(call.sid)
# print("done")

# Download the helper library from https://www.twilio.com/docs/python/install
import time
from pprint import pprint
# from twilio.rest import Client


# # Your Account Sid and Auth Token from twilio.com/console
account_sid = 'AC6f773e33d173baceaddd67ddc7f3d124'
auth_token = '67d7171a58c916ab3531e8a572aa24cc'
client = Client(account_sid, auth_token)
call = client.calls.create(
                        method='GET',
                        # status_callback='https://fordaniel.pythonanywhere.com/',
                        # status_callback_event=['initiated', 'completed', 'busy'],
                        # status_callback_method='POST',
                        url='http://demo.twilio.com/docs/voice.xml',
                        to ='+16505461126',
                        from_='+16508806260'
                    )
time.sleep(3)
call_sid = call.sid 
# # client.calls(call.sid).delete()
# call = client.calls(call.sid) 

# print(call.to)
# from twilio.rest import Client


# # Your Account Sid and Auth Token from twilio.com/console
# account_sid = 'AC6f773e33d173baceaddd67ddc7f3d124'
# auth_token = '67d7171a58c916ab3531e8a572aa24cc'
# client = Client(account_sid, auth_token)

calls = client.calls.list(
	status="busy"
)
# print()
# print(calls[-1].status)
for busy_call in calls:
	if busy_call.sid == call_sid:
		print("busy")
# print(len(calls), calls[0].status, "calls")

# recent = calls[-1]
# print(recent.status)

	

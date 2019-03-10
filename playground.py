***REMOVED***
***REMOVED***
***REMOVED***


account_sid = 'AC6f773e33d173baceaddd67ddc7f3d124'
auth_token = '67d7171a58c916ab3531e8a572aa24cc'
***REMOVED***

***REMOVED***
	call = client.calls.create(
		method='GET',
		url='http://demo.twilio.com/docs/voice.xml',
		to =phone_number,
		from_='+16508806260'
	)
	time.sleep(3)
	call_sid = call.sid 
	calls = client.calls.list(status="busy")
	for busy_call in calls:
		if busy_call.sid == call_sid:
		 	return "busy"
	return "not busy"

# print(check_number_busy("+16505461126"))
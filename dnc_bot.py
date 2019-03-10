# This the bot that will facitilitate the user's submission of their phone number
# and eamil address to the National Do Not Call Registry
# This will be impleneted as a page in the app

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import flask
from flask import Flask, request
#

#
app = Flask(__name__)
@app.route("/", methods=["GET"])
def recieve():
	print(request.args)
	if request.args.get("phone_num") is None:
		return "No phone number given"
	if request.args.get("email") is None:
		return "No email given"
	status = submitForm(request.args.get("phone_num"), request.args.get("email"))
	if (status):
		print("success")
		return "success"
	return "failure"

def submitForm(phone_num_input, email_input):

	#saved in the same location as the chromedriver.exe executable we extracted earlier.
	# browser = webdriver.Chrome(executable_path="./chromedriver")
	browser = webdriver.Chrome(executable_path="/Users/DanielLongo"
											   "c  1/Desktop/SpamCall/chromedriver orig")
	# browser.get(('https://www.donotcall.gov/register/reg.aspx'))

	register_here_xpath = '//*[@id="ContentPlaceHolder1_Button1"]'
	register_here_btn = browser.find_element_by_xpath(register_here_xpath)
	register_here_btn.click()

	phone_number_xpath = '//*[@id="ContentPlaceHolder1_PhoneNumberTextBox1"]'
	phone_number_box = browser.find_element_by_xpath(phone_number_xpath)
	phone_number_box.send_keys(phone_num_input)

	email_xpath = '//*[@id="ContentPlaceHolder1_EmailAddressTextBox"]'
	email_box = browser.find_element_by_xpath(email_xpath)
	email_box.send_keys(email_input)

	email_confirm_xpath = '//*[@id="ContentPlaceHolder1_ConfirmEmailAddressTextBox"]'
	email_confirm_box = browser.find_element_by_xpath(email_confirm_xpath)
	email_confirm_box.send_keys(email_input)

	submit_xpath = '//*[@id="ContentPlaceHolder1_SubmitButton1"]'
	submit_btn = browser.find_element_by_xpath(submit_xpath)
	submit_btn.click()

	try:
		register_official_xpath = '//*[@id="ContentPlaceHolder1_RegisterButton"]'
		register_official_btn = browser.find_element_by_xpath(register_official_xpath)
		register_official_btn.click()
		return True
	except:
		False
	return False
#
***REMOVED***
	# app.run(host="0.0.0.0", port=8080)
	the_phone_num_input = '4154567890'
	the_email_input = 'something1234@gmail.com'
	print(submitForm(the_phone_num_input, the_email_input))
	# print("Finished")
	# ("Success. Please confirm your submission to this registry via an email you will receive within 72 hours.")
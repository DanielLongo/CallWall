# from lxml import html
# import requests
# page = requests.get("https://who-calledme.com/Number/800-531-8722")
# print(page)
# tree = html.fromstring(page.content)
# # danger = tree.xpath('//*[@id="NNForm"]/div[4]/div[1]/div/div/div/div[2]/div[1]/div[2]')
# num_searches = tree.xpath('//*[@id="NNForm"]/div[4]/div[1]/div/div/div/div[2]/div[3]/div[2]')
# print(num_searches)
# # print(danger)

# import urllib
from urllib.request import Request, urlopen
from bs4 import BeautifulSoup
import urllib.request

class AppURLopener(urllib.request.FancyURLopener):
    version = "Mozilla/5.0"

def check_number_danger(number):
	if len(str(number)) == 10:
		number = int("1" + str(number))
	assert(len(str(number)) == 11), "Number too short"
	string_number = str(number)[1:4] + "-" + str(number)[4:7] + "-" + str(number)[7:]
	url = "https://who-calledme.com/Number/" + string_number
	opener = AppURLopener()
	response = opener.open(url)
	soup = BeautifulSoup(response, "html.parser")
	name_box = soup.find("div", attrs={"class": "dataColumn"})
	rate = name_box.text[1:]
	print(rate)
	if rate == "Dangerous":
		print("5")

check_number_danger(18005318722)




# req = Request(, headers={'User-Agent': 'Mozilla/5.0'})
# webpage = urlopen(req).read()
# tree = html.fromstring(page.content)
# num_searches = tree.xpath('//*[@id="NNForm"]/div[4]/div[1]/div/div/div/div[2]/div[3]/div[2]')
# print(num_searches)
# soup = BeautifulSoup(page, "html.parser")
# page = urllib.request.urlopen("https://who-calledme.com/Number/800-531-8722")
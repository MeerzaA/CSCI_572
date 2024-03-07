import time
from bs4 import BeautifulSoup 
from time import sleep 
import requests 
from random import randint 
from html.parser import HTMLParser 

USER_AGENT = {'User-Agent': 'Mozilla/5.0 AppleWebKit/537.36 (KHTML, like Gecko; compatible; bingbot/2.0; Chrome/W.X.Y.Z Safari/537.36)'}

class SearchEngine: 
    @staticmethod 
    def search(query, sleep=True):
        print(query)
        if sleep: # Prevents loading too many pages too soon 
            time.sleep(randint(5, 15)) 
        temp_url = '+'.join(query.split()) #for adding + between words for the query 
        url = 'http://www.bing.com/search?q=' + temp_url + "&count=60"
        soup = BeautifulSoup(requests.get(url, headers=USER_AGENT).text, "html.parser") 
        new_results = SearchEngine.scrape_search_result(soup) 
        return new_results 

    @staticmethod 
    def scrape_search_result(soup): 
        raw_results = soup.find_all("li", attrs={"class": "b_algo"})
        results = [] 
        #implement a check to get only 10 results and also check that URLs must not be duplicated 
        for result in raw_results: 
            link = result.find('a').get('href') 
            print(link)
            results.append(link) 
            if len(results) == 10: 
                break
        
        return results 
    


def Read_json():
    
    with open("3QQQ.txt", "r") as f:

        for lines in f:
            lines.rstrip()
            print(lines)



# def Read Query from Google file Compare json

# def Check Urls


def main():

    Read_json()


if __name__ == "__main__":
    main()
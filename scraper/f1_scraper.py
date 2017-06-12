from bs4 import BeautifulSoup
from helper import write_file, create_table
import urllib.request
import sys

# Constant page locations for F1 Results
F1 = 'https://www.formula1.com'
PRACTICE1 = 'practice-1.html'
PRACTICE2 = 'practice-2.html'
PRACTICE3 = 'practice-3.html'
QUALIFYING = 'qualifying.html'
STARTING = 'starting-grid.html'
FASTEST = 'fastest-laps.html'
RACE = 'race-result.html'
PITS = 'pit-stop-summary.html'

# Parse command line option for year. This scraper only supports the F1 website
# from 2006 to 2017
YEAR = sys.argv[1]
assert len(YEAR) == 4
assert 2006 <= int(YEAR) <= 2017

# Read raw HTML for main results page for given year
url = 'https://www.formula1.com/en/results.html/' + YEAR + '/races.html'
f = urllib.request.urlopen(url)
html = f.read()

# Get the links we need to scrape
to_parse = set()
for text in BeautifulSoup(html, 'html.parser').find_all('a'):
    if '/races/' in text.get('href') and RACE in text.get('href'):
        to_parse.add(text.get('href'))

# Go through each of the links and parse them
count = 1
tot = len(to_parse)
for url in to_parse:
    # Get the appropriate fields of the URL
    holders = url.split('/')
    COUNTRY = holders[6]
    holders.pop(7)

    # Create the temporary URL and base location
    temp_url = F1 + '/'.join(holders) + '/'
    base_loc = 'data/' + YEAR + '/' + COUNTRY + '/'

    print('\n=====', COUNTRY.upper(), '=====', str(count), 'out of', str(tot))
    # Get and write PRACTICE sessions
    write_file(create_table(temp_url + PRACTICE1), base_loc
               + PRACTICE1.replace('.html', '.txt'))
    write_file(create_table(temp_url + PRACTICE2), base_loc
               + PRACTICE2.replace('.html', '.txt'))
    write_file(create_table(temp_url + PRACTICE3), base_loc
               + PRACTICE3.replace('.html', '.txt'))
    # Get and write QUALIFYING data
    write_file(create_table(temp_url + QUALIFYING), base_loc
               + QUALIFYING.replace('.html', '.txt'))
    # Get and write RACE data
    write_file(create_table(temp_url + STARTING), base_loc
               + STARTING.replace('.html', '.txt'))
    write_file(create_table(temp_url + FASTEST), base_loc
               + FASTEST.replace('.html', '.txt'))
    write_file(create_table(temp_url + RACE), base_loc
               + RACE.replace('.html', '.txt'))
    # Get and write PIT STOP data
    write_file(create_table(temp_url + PITS), base_loc
               + PITS.replace('.html', '.txt'))
    count += 1

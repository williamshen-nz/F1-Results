import os
import codecs
from bs4 import BeautifulSoup
import re
import urllib.request


def write_file(text, filename):
    """
    Write given text output to a given file
    :param text: the text
    :param filename: path to save the file to
    :return: None
    """
    if text == '':
        print('- no results found! Ignoring...')
        return
    print('- writing output to', filename)
    # Create the directory and file if it doesn't exist
    os.makedirs(os.path.dirname(filename), exist_ok=True)
    # Open the file with UTF-8 encoding to support accents on letters
    with codecs.open(filename, 'w', encoding='utf-8') as f:
        f.write(text)
    print('- file written successfully!')


def fix_the_s(time):
    """
    Fix the seconds spacing when parsing the table
    :param time: A potential string with 1.23 s which we want to turn to 1.23s
    :return: the converted string
    """
    if '.' in time and '+' in time:
        return time.replace(' ', '')
    else:
        return time


def cell_text(cell):
    return ' '.join(cell.stripped_strings)


def create_table(url):
    """
    Given a URL, parse the table on the page and return it as a semi-
    comma-separated-value string with any notes
    :param url: url to get the HTML from
    :return: the resulting built string
    """
    # Read HTML or URL
    print('Processing:', url)
    f = urllib.request.urlopen(url)
    html = f.read()
    soup = BeautifulSoup(html, 'html.parser')

    # Get any notes for the session
    notes = ''
    for note in soup.findAll('p', class_='note'):
        notes += note.text
    # Remove blank lines
    notes = ''.join([s for s in notes.strip().splitlines(True) if s.strip()])

    # Read and generate table string
    for table in soup.find_all('table'):
        i = 0
        tab = ''
        for row in table.find_all('tr'):
            col = map(cell_text, row.find_all(re.compile('t[dh]')))
            if i == 0:
                i += 1
                continue

            # Remove unnecessary fields
            i += 1
            value = list(col)
            value.pop(0)
            value.pop(len(value) - 1)

            # Fix the seconds spacing for race and practice sessions
            if 'race-result.html' in url or 'practice-' in url:
                value[5] = fix_the_s(value[5])

            # Build the resulting string
            res = ''
            for v in value:
                res += v + ','
            res = res[0:len(res) - 1] + '\n'
            tab += res

        # Add any notes onto the resulting string
        if notes != '':
            tab += notes + '\n'
        return tab

    return ''

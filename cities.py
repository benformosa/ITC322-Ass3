#!/usr/bin/env python
# Cities.py
# Ben Formosa
# tested on python 3.2.1
#
# Generates two files, one a list of city names and indexes, the other a list of distances between cities, by index
# The script takes three optional arguments, the first is a string to use as a prefix to the file names,
#  the second is the number of cities to generate,
#  the third is the number of paths to generate

import argparse
import random
import string

VOWELS = 'aeiou'
CONSONANTS = ''.join(c for c in string.ascii_lowercase if c not in VOWELS)

def main():
	parser = argparse.ArgumentParser(description='Create the distance and index files for CSU 2013 Session 1 ITC233 Assignment 3')

	parser.add_argument('prefix', type=str, nargs='?', default='', help='string to prefix output file names')
	parser.add_argument('cities', type=int, nargs='?', default=20, help='total number of cities to generate, default 20')
	parser.add_argument('paths', type=int, nargs='?', default=30, help='total number of paths to generate, default 20')
	args = parser.parse_args()

	prefix = args.prefix

	if prefix != '':
		prefix = prefix + '_'

	f_distance = open(prefix + 'distance.txt', 'w')
	f_index = open(prefix + 'index.txt', 'w')

	# create index.txt
	c = citynames(args.cities)
	for i in c:
		line = str(c[i]) + ' ' + i
		print(line, file=f_index)

	# create distance.txt
	print(args.cities, file=f_distance)
	p = paths(args.cities, args.paths)
	for i in p:
		line = str(i[0]) + ' ' + str(i[1]) + ' ' + str(p[i])
		print(line, file=f_distance)

def path(c):
	source = random.randrange(0, c)
	destination = source

	# assign a destination that is different to the source
	while source == destination:
		destination = random.randrange(0, c)

	# the index of the source city will always be lower than that of the destination city
	if source > destination:
		source, destination = destination, source

	distance = random.randrange(1, 999)

	return (source, destination, distance)

def paths(c, p):
	# the key for the dictionary is a tuple (source, destination), the value is the distance
	dict = {}

	# create paths until we have enough
	while len(dict) < p:
		temp = path(c)
		# only add the path if the source and destination do not already have a distance
		if (temp[0], temp[1]) not in dict:
			dict[(temp[0], temp[1])] = temp[2]

	return dict

def cityname():
	name = ''
	length = random.randrange(3,6)
	vowel = False

	# city names are alternated vowels and consonants
	while len(name) < length:
		if vowel:
			name += random.choice(VOWELS)
		else:
			name += random.choice(CONSONANTS)
		vowel = not vowel

	return name.capitalize()

def citynames(c):
	# the key for the dictionary is the city name, the value is the index
	dict = {}

	while len(dict) < c:
		temp = cityname()
		if (temp) not in dict:
			dict[temp] = len(dict)
			
	return dict

if __name__ == "__main__":
	main()

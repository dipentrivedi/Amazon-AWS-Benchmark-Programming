#!/bin/bash

javac samplesModule.java 

threads = $1

	for (( i = 0; i < 600; i++ ))
	do
		echo "Samples are taken for " $i
		java samplesModule >> samples.txt
		
	done
	

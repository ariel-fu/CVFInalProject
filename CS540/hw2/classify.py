import os
import math

# These first two functions require os operations and so are completed for you
# Completed for you

def load_training_data(vocab, directory):
    """ Create the list of dictionaries """
    top_level = os.listdir(directory)
    dataset = []
    for d in top_level:
        if d.startswith('.'):
            # ignore hidden files
            continue
        if d[-1] == '/':
            label = d[:-1]
            subdir = d
        else:
            label = d
            subdir = d+"/"
        files = os.listdir(directory+subdir)
        for f in files:
            bow = create_bow(vocab, directory+subdir+f)
            dataset.append({'label': label, 'bow': bow})
    return dataset

# Completed for you
def create_vocabulary(directory, cutoff):
    """ Create a vocabulary from the training directory
        return a sorted vocabulary list
    """
    top_level = os.listdir(directory)
    vocab = {}
    for d in top_level:
        if d.startswith('.'):
            # ignore hidden files
            continue
        subdir = d if d[-1] == '/' else d+'/'
        files = os.listdir(directory+subdir)
        for f in files:
            with open(directory+subdir+f, 'r', encoding='utf-8') as doc:
                for word in doc:
                    word = word.strip()
                    if not word in vocab and len(word) > 0:
                        vocab[word] = 1
                    elif len(word) > 0:
                        vocab[word] += 1
    return sorted([word for word in vocab if vocab[word] >= cutoff])

# The rest of the functions need modifications ------------------------------
# Needs modifications
def create_bow(vocab, filepath):
    """ Create a single dictionary for the data
        Note: label may be None
    """
    bow = {}
    noneKey = 'None'
    bow[noneKey] = 0
    # TODO: add your code here
    # open and read the file
    with open(filepath, 'r', encoding='utf-8') as doc:
        for key in doc:
            key = key.strip()
            if(key in vocab):
                if key in bow:
                    bow[key] += 1
                else:
                    bow[key] = 1
            else:
                bow[noneKey] += 1
    return bow

# Needs modifications
def prior(training_data, label_list):
    """ return the prior probability of the label in the training set
        => frequency of DOCUMENTS
    """
    totalNumDocs = 0
    numLabel2016 = 0
    numLabel2020 = 0

    for doc in training_data:
        #print(doc)
        
        if doc['label'] == '2020':
            numLabel2020 += 1
        else:
            numLabel2016 += 1
        totalNumDocs += 1
 
    # TODO: testing
    # print("-- 2016 --")
    # print(numLabel2016)
    # print("-- 2020 --")
    # print(numLabel2020)
    # print("--totalNumDocs--")
    # print(totalNumDocs)
 
    smooth = 1  # smoothing factor
    # get the probability of 2016
    prob2016 = math.log((numLabel2016+smooth)/(totalNumDocs+smooth+smooth))
    # get the probability of 2020
    prob2020 = math.log((numLabel2020+smooth)/(totalNumDocs+smooth+smooth))

    # fill in probability
    logprob = {}
    logprob['2020'] = prob2020
    logprob['2016'] = prob2016
    return logprob  

# Needs modifications
def p_word_given_label(vocab, training_data, label):
    """ return the class conditional probability of label over all words, with smoothing """
    # TODO: add your code here
    word_prob = {}
    smooth = 1  # smoothing factor
    
    # set up word_prob to contain all the words inside vocab with a frequency of 0
    for word in vocab:
        word_prob[word] = 0
    noneKey = 'None'
    word_prob[noneKey] = 0
    
    
    vocabSize = getSize(vocab)
    # TODO: testing
    #print(vocabSize)
    totalNumWords = 0
    # get the count for n_w
    # loop through all the data sets in the training_data
    for dataSet in training_data:
        currBow = dataSet['bow']
        #print(currBow)
        currLabel = dataSet['label']
        # if is the correct label, get the word count
        if currLabel == label:
            for word in currBow:
                # TODO: testing
                # print(currBow)
                # check if the curr word is in the vocabulary
                if word in vocab:
                    # TODO: testing
                    # print(word)
                    # determine whether the curr word has already appeared or not
                    if word in word_prob:
                    # add the number of times the current word appears in the current document to the word's running total appearances
                        word_prob[word] += currBow[word]
                        # TODO: testing
                        # print("already in here")
                    else:
                        # if not, set the word's total appearances the number of times the current word appears in the current document
                        # TODO: testing
                        # print("adding")
                        word_prob[word] = currBow[word]
                        # TODO: testing
                        # print(word_prob)
                        # print("--")
                    totalNumWords += currBow[word]      
                else:
                    # current word is not in the dictionary - add to 'None'
                    if noneKey in word_prob.keys():
                        word_prob[noneKey] += currBow[word]
                    else:
                        word_prob[noneKey] = currBow[word]
                    # add to the count of total words
                    totalNumWords += currBow[word]
                


    # print(word_prob)

    for wordCount in word_prob:
        # add the smooth
        count = word_prob[wordCount] + smooth
        # print(wordCount)
        # print("count")
        # print(count)
        # calculate the divisor
        divisor = totalNumWords + vocabSize + smooth
        # print(totalNumWords)
        # print("divisor")
        # print(divisor)
        # print("prob before ln")
        # print(count/divisor)
        # divide the count by the divisor
        # take the natural log
        prob = math.log(count/divisor)
        # print("prob after ln")
        # print(prob)
        word_prob[wordCount] = prob



    return word_prob

    # find the total word count
    # total_word = {}
    # for dataSet in training_data:
    #     currBow = dataSet['bow']
    #     total_word = getWordCount(total_word, currBow, vocab)

    # for word in word_prob:
    #     # get the total of that word from total_word
    #     wordTotal = total_word[word]
    #     count = word_prob[word]/(wordTotal + vocabSize + smooth)
    #     count = math.log(count)
    # totalWordCount = 0
    # for element in training_data:
    #     bow = element['bow']
    #     totalWordCount += getSize(bow)
    # print("TOTAL WORD COUNT")
    # print(totalWordCount)
    # print("total vocab size")
    # print(vocab)
    # print(vocabSize)
    # # calculate ln(n + |V| + 1)
    # divisor = totalWordCount + vocabSize + 1

    # # probability of each word = ln(n_word + 1) - ln(n + |V| + 1) by log properties
    # for word in word_prob:
    #     # calculate ln(n_word+1) - ln(n + |V| + 1) for each word
    #     currWordCount = word_prob[word]
    #     # print(word)
    #     # print(currWordCount)
    #     # print("---")
        
    #     word_prob[word] = (currWordCount+1)/(totalWordCount + vocabSize + 1)
    
    # probability = (total word count with label x + 1)/(total word count + vocabulary word count + 1)

# helper method to get the total size in a bow
def getSize(bow):
    """ return the size given a bow """
    size = 0
    for word in bow:
        size += 1
    return size


##################################################################################
# Needs modifications
def train(training_directory, cutoff):
    """ return a dictionary formatted as follows:
            {
             'vocabulary': <the training set vocabulary>,
             'log prior': <the output of prior()>,
             'log p(w|y=2016)': <the output of p_word_given_label() for 2016>,
             'log p(w|y=2020)': <the output of p_word_given_label() for 2020>
            }
    """
    retval = {}
    label_list = [f for f in os.listdir(
        training_directory) if not f.startswith('.')]  # ignore hidden files
    
    # TODO: add your code here
    # get the prior
    vocab = create_vocabulary(training_directory, cutoff)
    retval["vocabulary"] = vocab
    # TODO: make these into a string????
    # print(type(training_directory))
    training_data = load_training_data(vocab, training_directory)
    retval['log prior'] = prior(training_data, label_list)
    # for every single label in label_list, calculate the probability of p(w | label)
    for label in label_list:
        # calculate the probability of p(w | label)
        str = 'log p(w|y=' + label + ")"
        retval[str] = p_word_given_label(vocab, training_data, label)

    return retval

# Needs modifications
def classify(model, filepath):
    """ return a dictionary formatted as follows:
            {
             'predicted y': <'2016' or '2020'>,
             'log p(y=2016|x)': <log probability of 2016 label for the document>,
             'log p(y=2020|x)': <log probability of 2020 label for the document>
            }
    """

    retval = {}
    # TODO: add your code here
    # get vocab from model
    vocab = model['vocabulary']
    # create bow: def create_bow(vocab, filepath)
    words = create_bow(vocab, filepath)
    # get word probability from model
    model_prob2020 = model['log p(w|y=2020)']
    # TODO: testing
    # print("model_prob2020")
    # print(type(model_prob2020))
    # print(model_prob2020)
    # print("--------------")
    model_prob2016 = model['log p(w|y=2016)']
    # TODO: testing
    # print("model_prob2016")
    # print(model_prob2016)
    # print("--------------")
    # keep track of sum of probabilities
    sum2016 = 0 
    sum2020 = 0
    for word in words:
        # get probability of word being from 2020
        print("---------")
        print(word)
        print("---------")
        wordProb2020 = words[word] * getWordProb(word, model_prob2020)
        sum2020 += wordProb2020
        # TODO: testing
        # print("curr sum 2020")
        # print("add:")
        # print(wordProb2020)
        # print(sum2020)
        # print("--------------")
        # get probability of word being from 2016
        wordProb2016 = words[word] * getWordProb(word, model_prob2016)
        sum2016 += wordProb2016
        # TODO: testing
        # print("curr sum 2016")
        # print("add:")
        # print(wordProb2016)
        # print(sum2016)
        # print("--------------")
    
    # get the priors
    priors = model['log prior']
    # add the priors
    sum2020 += priors['2020']
    # TODO: testing
    # print("--- priors ---")
    # print(priors['2020'])
    sum2016 += priors['2016']
    # TODO: testing
    # print(priors['2016'])
    # print("--------------")

    # get the max of sum2016 and sum2020
    if(sum2016 > sum2020):
        mostLikely = '2016'
    else:
        mostLikely = '2020'

    # set the respective values of log P(y=2020|x), log p(y=2016|x), predicted y
    retval['log p(y=2020|x)'] = sum2020
    retval['log p(y=2016|x)'] = sum2016
    retval['predicted y'] = mostLikely
    # return prediction
    return retval

# helper method to search for a target word's probabilit in a model
def getWordProb(word, model_prob):
    """ return the probability of a word given a model """
    for wordProb in model_prob:
        if(wordProb == word):
            return model_prob[wordProb]


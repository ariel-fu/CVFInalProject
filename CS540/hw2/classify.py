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
    noneKey = None

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
                if noneKey in bow:
                    bow[noneKey] += 1
                else:
                    bow[noneKey] = 1
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
        
        if doc['label'] == '2020':
            numLabel2020 += 1
        else:
            numLabel2016 += 1
        totalNumDocs += 1
 
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
    noneKey = None
    word_prob[noneKey] = 0
    
    
    vocabSize = getSize(vocab)
    totalNumWords = 0
    # get the count for n_w
    # loop through all the data sets in the training_data
    for dataSet in training_data:
        currBow = dataSet['bow']
        currLabel = dataSet['label']
        # if is the correct label, get the word count
        if currLabel == label:
            for word in currBow:
                # check if the curr word is in the vocabulary
                if word in vocab:
                    # determine whether the curr word has already appeared or not
                    if word in word_prob:
                    # add the number of times the current word appears in the current document to the word's running total appearances
                        word_prob[word] += currBow[word]
                    else:
                        # if not, set the word's total appearances the number of times the current word appears in the current document
                        word_prob[word] = currBow[word]
                    totalNumWords += currBow[word]      
                else:
                    # current word is not in the dictionary - add to 'None'
                    if noneKey in word_prob.keys():
                        word_prob[noneKey] += currBow[word]
                    else:
                        word_prob[noneKey] = currBow[word]
                    # add to the count of total words
                    totalNumWords += currBow[word]

    for wordCount in word_prob:
        # add the smooth
        count = word_prob[wordCount] + smooth
        # calculate the divisor
        divisor = totalNumWords + vocabSize + smooth
        # divide the count by the divisor
        # take the natural log
        prob = math.log(count/divisor)
        word_prob[wordCount] = prob



    return word_prob

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
    model_prob2016 = model['log p(w|y=2016)']
    # keep track of sum of probabilities
    sum2016 = 0 
    sum2020 = 0
    for word in words:
        # get probability of word being from 2020
        wordProb2020 = words[word] * getWordProb(word, model_prob2020)
        sum2020 += wordProb2020
        # get probability of word being from 2016
        wordProb2016 = words[word] * getWordProb(word, model_prob2016)
        sum2016 += wordProb2016
    # get the priors
    priors = model['log prior']
    # add the priors
    sum2020 += priors['2020']
    sum2016 += priors['2016']

    # get the max of sum2016 and sum2020
    if(sum2016 >= sum2020):
        mostLikely = '2016'
    else:
        mostLikely = '2020'

    # set the respective values of predicted y, log p(y=2016|x), log p(y=2020|x)
    retval['predicted y'] = mostLikely
    retval['log p(y=2016|x)'] = sum2016
    retval['log p(y=2020|x)'] = sum2020
    
    
    # return prediction
    return retval

# helper method to search for a target word's probabilit in a model
def getWordProb(word, model_prob):
    """ return the probability of a word given a model """
    for wordProb in model_prob:
        if(wordProb == word):
            return model_prob[wordProb]
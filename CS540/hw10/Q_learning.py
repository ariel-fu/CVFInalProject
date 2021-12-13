import gym
import random
import numpy as np
import time
from collections import deque
import pickle


from collections import defaultdict


EPISODES =   20000
LEARNING_RATE = .1
DISCOUNT_FACTOR = .99
EPSILON = 1
EPSILON_DECAY = .999



def default_Q_value():
    return 0


if __name__ == "__main__":

    random.seed(1)
    np.random.seed(1)
    env = gym.envs.make("FrozenLake-v0")
    env.seed(1)
    env.action_space.np_random.seed(1)


    Q_table = defaultdict(default_Q_value) # starts with a pessimistic estimate of zero reward for each state.

    episode_reward_record = deque(maxlen=100)


    for i in range(EPISODES):
        episode_reward = 0

        #TODO PERFORM Q LEARNING
        
        # restart the state
        current_state = env.reset()
        done = False
        while done == False:
            ## TEST
            env.render()
            time.sleep(0.5)
            # sample a float between 0 and 1
            p = random.uniform(0, 1)
            # if the float < epsilon:
            if(p < EPSILON):
                # take a random action
                action = env.action_space.sample()
            # else:
            else:
                # take the action with the highest Q_table(s, a) value
                prediction = np.array([Q_table[(current_state, index)] for index in range(env.action_space.n)])
                action = np.argmax(prediction)
            
            # take a step
            next_state, reward, done, _ = env.step(action)

            # append reward to total of the episode
            episode_reward += reward
            if(done):
                break
            
            # update Q values
            # if not done:
            # Q = Q_table(state, action) + alpha(reward + gamma(max(Q_table(next state, next action))) - Q_table(state, action))
            # a_prime = np.array([(Q_table[(next_state, index)] for index in range(env.action_space.n))])    
            # learned_value = DISCOUNT_FACTOR * np.max()
            # learned_value = DISCOUNT_FACTOR * np.max(Q_table[(next_state)])
            
            ## get the max value
            next_states = np.array([Q_table[(next_state, index)] for index in range(env.action_space.n)])
            learned_value = DISCOUNT_FACTOR * np.max(next_states)

            ## update Q_table
            Q_table[current_state, action] = Q_table[current_state, action] + LEARNING_RATE*((reward + learned_value) - Q_table[current_state, action])

            # update the state
            current_state = next_state
            
        
        # update Q
        # Q = Q_table(state, action) + alpha(reward - Q_table(state, action))
        Q_table[current_state, action] = Q_table[current_state, action] + LEARNING_RATE*(reward - Q_table[current_state, action])
        # append the episode's reward
        episode_reward_record.append(episode_reward)
        # update epsilon
        EPSILON = np.exp(-(1-EPSILON_DECAY)*i)

        if i%100 ==0 and i>0:
            print("LAST 100 EPISODE AVERAGE REWARD: " + str(sum(list(episode_reward_record))/100))
            print("EPSILON: " + str(EPSILON) )
    
    
    ####DO NOT MODIFY######
    model_file = open('Q_TABLE.pkl' ,'wb')
    pickle.dump([Q_table,EPSILON],model_file)
    #######################
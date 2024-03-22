
import matplotlib.pyplot as plt
import numpy as np

# Sample data for a simple line
x = np.array([1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000])
insMax = np.array([1, 3, 4, 8, 8, 11, 13, 15, 17, 18])
insAve = np.array([1, 2.4, 3.1, 5.58, 6.48, 8.68, 9.7, 11.9, 12.9, 14.4])
insThrt = np.array([0,2.32, 3.32, 5.64, 6.64, 8.97, 9.97, 12.29, 13.29, 15.61])

srchMax = np.array([1,3,4,7,8,11,12,15,16,18])
srchAve = np.array([1,2,3,5,6,9,10,11,12,13])
srchThrt = np.array([0, 2.32, 3.32,5.64, 6.64, 8.97, 9.97, 12.29, 13.29, 15.61])


# Basic line plot
# plt.plot(x, insMax)
# plt.plot(x, insAve)
# plt.plot(x, insThrt)
plt.plot(x,srchMax)
plt.plot(x,srchAve)
plt.plot(x,srchThrt)

# Customize appearance (optional)
plt.xlabel('n')
plt.ylabel('Operations')
plt.title('Search time complexity (orange: Ave, blue: Max, green: Theoretical)')

# Show the plot
plt.show()

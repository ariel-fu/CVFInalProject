import pandas as pd
import dask.dataframe as dataframe
import dask.datasets as datasets

df = pd.DataFrame({
    "Name": ["Mercutio", "Tybalt", "Lady Montague"],
    "Age": [3, 2, 4],
    "Fur": ["Grey", "Grey", "White"]
})

print("pandas\n")
print(df)
# define a groupby computation
print(df.groupby('Fur'))
print(df.groupby('Fur').Age)
print(df.groupby('Fur').Age.mean())

print("\n --- \n")
print("DF\n")
# problem: pandas evaluates the data right away
# with more data than what can be held in memory, this leads to many issues
# to solve this, we turn the pandas table into a Dask dataframe & evaluate "lazily"
dask_df = dataframe.from_pandas(df, npartitions=1)
# df only returns a schema of the dataset, not the actual value
print(dask_df)
print(dask_df.groupby("Fur").Age.mean())

# df returns the actual schema when .operate is called
print(dask_df.groupby("Fur").Age.mean().compute())

# write the Dask DataFrame to Parquet
df = datasets.timeseries(
  "2000-01-01",
  "2000-01-02",
  freq="1h",
  partition_freq="2h"
)

print(df.npartitions)
print(df)

df.to_parquet("test.parquet")